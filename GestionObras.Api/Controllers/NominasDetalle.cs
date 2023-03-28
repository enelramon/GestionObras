using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using GestionObras.Api.DAL;
using GestionObras.Api.Models;

namespace GestionObras.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class NominasDetalleController : ControllerBase
    {
        private readonly Contexto _context;

        public NominasDetalleController(Contexto context)
        {
            _context = context;
        }

        // GET: api/NominasDetalle
        [HttpGet]
        public async Task<ActionResult<IEnumerable<NominasDetalle>>> GetNominasDetalle()
        {
            if (_context.NominasDetalle == null)
            {
                return NotFound();
            }
            return await _context.NominasDetalle.ToListAsync();
        }

        // GET: api/NominasDetalle/5
        [HttpGet("{id}")]
        public async Task<ActionResult<NominasDetalle>> GetNominasDetalle(int id)
        {
            if (_context.NominasDetalle == null)
            {
                return NotFound();
            }

            var nominasDetalle = await _context.NominasDetalle.FindAsync(id);

            if (nominasDetalle == null)
            {
                return NotFound();
            }

            return nominasDetalle;
        }

        // PUT: api/NominasDetalle/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutNominasDetalle(int id, NominasDetalle nominasDetalle)
        {
            if (id != nominasDetalle.Id)
            {
                return BadRequest();
            }

            _context.Entry(nominasDetalle).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!NominasDetalleExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/NominasDetalle
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<NominasDetalle>> PostNominasDetalle(NominasDetalle nominasDetalle)
        {
            if (_context.NominasDetalle == null)
            {
                return Problem("Entity set 'Contexto.NominasDetalle'  is null.");
            }
            _context.NominasDetalle.Add(nominasDetalle);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetNominasDetalle", new { id = nominasDetalle.Id }, nominasDetalle);
        }

        // DELETE: api/NominasDetalle/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteNominasDetalle(int id)
        {
            if (_context.NominasDetalle == null)
            {
                return NotFound();
            }
            var nominasDetalle = await _context.NominasDetalle.FindAsync(id);
            if (nominasDetalle == null)
            {
                return NotFound();
            }

            _context.NominasDetalle.Remove(nominasDetalle);
            await _context.SaveChangesAsync();

            return NoContent();
        }
        private bool NominasDetalleExists(int id)
        {
            return (_context.NominasDetalle?.Any(nd => nd.Id == id)).GetValueOrDefault();
        }
    }
}
